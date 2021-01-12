import React, { useEffect, useState } from "react";
import { Link } from "@reach/router";
import { Container } from "react-bootstrap";

import axios from "axios";
import config from "../config.js";

import "./article-prev.css";

const ArticlePreview = (props) => {
  const [article, setArticle] = useState({ author: {}, sight: {} });
  useEffect(() => {
    axios
      .get(config.API.BLOG + "resources/articles/" + props.id)
      .then((res) => {
        setArticle(res.data);
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);
  return (
    <div
      id="articlePreview"
      style={{
        marginTop: "20px"
      }}
    >
      <Container>
        <Link to="/">
          <i className="fas fa-arrow-left" /> Return back
        </Link>
        <h1 className="text-center">{article.title}</h1>
        <p>
          <i className="fas fa-calendar-alt" /> {article.publicationDate}
        </p>
        <p>
          <i className="fas fa-map-marker-alt" />{" "}
          {`${article.sight.city} - ${article.sight.name}`}
        </p>
        <hr />
        <p>{article.description}</p>
        <div className="text-right">
          <span>
            <i className="fas fa-at" />{" "}
            {`${article.author.firstname} ${article.author.lastname}`}
          </span>
        </div>
      </Container>
    </div>
  );
};

export default ArticlePreview;
