import React from "react";
import ReactDOM from "react-dom";
import { Router } from "@reach/router";

import "bootstrap/dist/css/bootstrap.min.css";

import HomePage from "./homepage/HomePage";
import NotFound from "./notfound/NotFound";
import StatsPage from "./statspage/StatsPage";
import BonusPage from "./bonuspage/BonusPage";
import ArticlePreview from "./article/ArticlePreview";
import NavBar from "./navbar/NavBar";

const App = () => {
  return (
    <>
      <NavBar />
      <Router>
        <HomePage path="/" />
        <ArticlePreview path="/articles/:id" />
        <BonusPage path="/bonus" />
        <StatsPage path="/statistics" />
        <NotFound default />
      </Router>
    </>
  );
};

ReactDOM.render(<App />, document.getElementById("root"));
