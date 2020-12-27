import React from "react";
import Article from "./Article";

class ArticlePreview extends Article {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <Article
        {...{
          preview: true
        }}
      />
    );
  }
}

export default ArticlePreview;
