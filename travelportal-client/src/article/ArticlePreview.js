import React from "react";

import { Link } from "@reach/router";
import { Container } from "react-bootstrap";

import "./article-prev.css";

const ArticlePreview = () => {
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
        <h1 className="text-center">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit
        </h1>
        <p>
          <i className="fas fa-calendar-alt" /> 01.01.2021
        </p>
        <p>
          <i className="fas fa-map-marker-alt" /> Wien - Location Somewhere
        </p>
        <hr />
        <p>
          Sed ut perspiciatis unde omnis iste natus error sit voluptatem
          accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae
          ab illo inventore veritatis et quasi architecto beatae vitae dicta
          sunt explicabo.
          <br />
          <br />
          Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut
          fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem
          sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor
          sit amet, consectetur, adipisci velit, sed quia non numquam eius modi
          tempora incidunt ut labore et dolore magnam aliquam quaerat
          voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem
          ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi
          consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate
          velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum
          fugiat quo voluptas nulla pariatur?
        </p>
        <div className="text-right">
          <span>
            <i className="fas fa-at" /> Stefan Miljevic
          </span>
        </div>
      </Container>
    </div>
  );
};

export default ArticlePreview;
