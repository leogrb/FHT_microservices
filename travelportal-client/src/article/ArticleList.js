import React, { useEffect, useState } from "react";
import ArticleItem from "./ArticleItem";
import axios from "axios";
import config from "../config.js";

const ArticleList = () => {
  const [data, setData] = useState([]);
  useEffect(() => {
    axios
      .get(config.API.BLOG + "resources/articles")
      .then((res) => {
        let articles = res.data;
        articles.sort((prev, next) => {
          return (
            new Date(next.publicationDate) - new Date(prev.publicationDate)
          );
        });
        setData(res.data);
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);
  return (
    <>
      {data.map((article) => (
        <ArticleItem key={`article-${article.id}`} {...article} />
      ))}
    </>
  );
};

export default ArticleList;
