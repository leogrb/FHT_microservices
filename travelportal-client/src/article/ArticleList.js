import React from "react";
import { CardColumns } from "react-bootstrap";
import ArticleItem from "./ArticleItem";

const retrieveData = () => {
  const sampleData = [
    {
      id: 1,
      title: "Ein Nachmittag im Labyrinth in Schönbrunn",
      description:
        "Es handelt sich um einen Wandelgang zwischen hohen und schmalen Hecken, ohne Sackgassen und Verirrungen, der zum „Lustwandeln“ einladen sollte.",
      publicationDate: "01.01.2021"
    },
    {
      id: 2,
      title: "Ein sonniges Wochenende in der Wachau",
      description:
        "Eine schöne Runde verspricht der St. Michael Rundweg von Spitz nach St. Michael und wieder retour. Rund 7 Kilometer Abwechslung mit Weingärten, Wälder und der Donau warten auf dich.",
      publicationDate: "02.01.2021"
    }
  ];
  return sampleData;
};

const ArticleList = () => {
  const data = retrieveData();
  return (
    <CardColumns>
      {data.map((article) => (
        <ArticleItem key={`article-${article.id}`} {...article} />
      ))}
    </CardColumns>
  );
};

export default ArticleList;
