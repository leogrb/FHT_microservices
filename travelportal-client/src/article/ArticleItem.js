import { Link } from "@reach/router";
import { truncate } from "lodash";
import React from "react";
import { Card } from "react-bootstrap";

const ArticleItem = (props) => {
  const { id, title, description, publicationDate } = props;
  const preparedDescription = truncate(description, {
    length: 100
  });
  return (
    <Card>
      <Card.Body>
        <Card.Title>{title}</Card.Title>
        <Card.Subtitle className="mb-2 text-muted">
          <i className="fas fa-calendar-alt" /> {publicationDate}
        </Card.Subtitle>
        <Card.Text>{preparedDescription}</Card.Text>
        <Card.Link as={Link} to={"articles/" + id}>
          Read more
        </Card.Link>
      </Card.Body>
    </Card>
  );
};

export default ArticleItem;
