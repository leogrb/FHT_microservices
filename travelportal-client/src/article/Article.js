import { Button } from "react-bootstrap";
import React from "react";
import { Container, Form } from "react-bootstrap";
import { isFunction } from "lodash";

class Article extends React.Component {
  constructor(props) {
    super(props);
    this.handleCreate = this.handleCreate.bind(this);
  }

  handleCreate(event) {
    event.preventDefault();
    // save data
    if (isFunction(this.props.callback)) {
      this.props.callback();
    }
  }

  render() {
    return (
      <Container>
        <Form style={{ marginTop: "20px" }}>
          <Form.Group controlId="titleInput">
            <Form.Label>Title:</Form.Label>
            <Form.Control type="text" placeholder="Enter article title..." />
          </Form.Group>
          <Form.Group controlId="descriptionInput">
            <Form.Label>Description:</Form.Label>
            <Form.Control
              as="textarea"
              rows={5}
              placeholder="Enter article description..."
            />
          </Form.Group>
          <Form.Group controlId="sightSelect">
            <Form.Label>Sight:</Form.Label>
            <Form.Control as="select">
              <option>Wien - Schloss Schönbrunn</option>
              <option>Salzburg - Festung Hohensalzburg</option>
            </Form.Control>
          </Form.Group>
          <Form.Group controlId="authorSelect">
            <Form.Label>Author:</Form.Label>
            <Form.Control as="select">
              <option>Leo Gruber</option>
              <option>Stefan Miljevic</option>
            </Form.Control>
          </Form.Group>
          <Form.Group controlId="datepickerInput">
            <Form.Label>Publication Date:</Form.Label>
            <Form.Control type="date" />
          </Form.Group>
          <Button variant="primary" type="submit" onClick={this.handleCreate}>
            Create article
          </Button>
        </Form>
      </Container>
    );
  }
}

export default Article;
