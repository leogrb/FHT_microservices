import axios from "axios";
import { isObject } from "lodash";
import React, { useEffect, useState } from "react";
import { Container } from "react-bootstrap";

import config from "../config.js";

const preparePayload = (data) => {
  const preparedData = {};
  for (let i = 0; i < data.length; i++) {
    const entry = data[i];
    const newStat = {
      year: entry.yearId,
      month: entry.monthId,
      clicks: entry.clicks
    };
    const key = "s-" + entry.sight.id;
    if (!isObject(preparedData[key])) {
      preparedData[key] = {
        sight: entry.sight,
        statistics: []
      };
    }
    preparedData[key].statistics.push(newStat);
  }
  return preparedData;
};

const StatsPage = () => {
  const [data, setData] = useState({});
  useEffect(() => {
    axios
      .get(config.API.STATISTICS + "resources/sight-statistics/")
      .then((res) => {
        const prepData = preparePayload(res.data);
        console.log(prepData);
        setData(prepData);
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);
  return (
    <Container
      style={{
        marginTop: "10px"
      }}
    >
      <h1>Statistics</h1>
      <hr />
      <div>
        {Object.keys(data).map((sightId) => (
          <div key={sightId}>
            <h3>{`${data[sightId].sight.city} - ${data[sightId].sight.name}`}</h3>
            <div
              className="row"
              style={{
                display: "flex"
              }}
            >
              {data[sightId].statistics.map((stat, index) => (
                <div
                  key={`stat-${index}`}
                  style={{
                    padding: "15px",
                    width: "150px"
                  }}
                >
                  <h5>{`${stat.year}/${stat.month}`}</h5>
                  <span>Clicks: {stat.clicks}</span>
                </div>
              ))}
            </div>
            <hr />
          </div>
        ))}
      </div>
    </Container>
  );
};

export default StatsPage;
