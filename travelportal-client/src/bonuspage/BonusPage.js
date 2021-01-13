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
      payout: entry.clicks / 100
    };
    const key = "a-" + entry.author.id;
    if (!isObject(preparedData[key])) {
      preparedData[key] = {
        author: entry.author,
        bonuses: []
      };
    }
    preparedData[key].bonuses.push(newStat);
  }
  return preparedData;
};

const BonusPage = () => {
  const [data, setData] = useState({});
  useEffect(() => {
    axios
      .get(config.API.BONUS + "resources/author-bonus/")
      .then((res) => {
        const prepData = preparePayload(res.data);
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
      <h1 className="text-center">Author Bonus</h1>
      <hr />
      <div>
        {Object.keys(data).map((authorId) => (
          <div key={authorId}>
            <h3>{`${data[authorId].author.firstname} ${data[authorId].author.lastname}`}</h3>
            <div
              className="row"
              style={{
                display: "flex"
              }}
            >
              {data[authorId].bonuses.map((stat, index) => (
                <div
                  key={`stat-${index}`}
                  style={{
                    padding: "15px",
                    width: "150px"
                  }}
                >
                  <h5>{`${stat.year}/${stat.month}`}</h5>
                  <span>Payout: {`${stat.payout.toFixed(2)}$`}</span>
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

export default BonusPage;
