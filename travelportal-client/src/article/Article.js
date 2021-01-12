import { Button } from "react-bootstrap";
import React from "react";
import { Container, Form } from "react-bootstrap";
import { isFunction, toInteger } from "lodash";
import axios from "axios";
import config from "../config.js";

class Article extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      article: {
        title: null,
        description: null,
        publicationDate: null,
        topNews: false,
        author: {},
        sight: {}
      },
      authors: [],
      authorIndex: 0,
      sights: [],
      sightIndex: 0
    };
    this.handleCreate = this.handleCreate.bind(this);
    this.handleChangeTitle = this.handleChangeTitle.bind(this);
    this.handleChangeDescription = this.handleChangeDescription.bind(this);
    this.handleChangeDate = this.handleChangeDate.bind(this);
    this.handleChangeSight = this.handleChangeSight.bind(this);
    this.handleChangeAuthor = this.handleChangeAuthor.bind(this);
  }

  componentDidMount() {
    // retrieve authors
    axios
      .get(config.API.BLOG + "resources/authors")
      .then((res) => {
        this.setState({
          authors: res.data
        });
      })
      .catch((err) => {
        console.error(err);
      });
    // retrieve sights
    axios
      .get(config.API.BLOG + "resources/sights")
      .then((res) => {
        this.setState({
          sights: res.data
        });
      })
      .catch((err) => {
        console.error(err);
      });
  }

  handleChangeTitle(event) {
    const { article } = this.state;
    article.title = event.target.value;
    this.setState({
      article
    });
  }

  handleChangeDescription(event) {
    const { article } = this.state;
    article.description = event.target.value;
    this.setState({
      article
    });
  }

  handleChangeDate(event) {
    const { article } = this.state;
    article.publicationDate = event.target.value;
    this.setState({
      article
    });
  }

  handleChangeSight(event) {
    const { article } = this.state;
    const index = toInteger(event.target.value);
    article.sight = this.state.sights[index];
    this.setState({
      article,
      sightIndex: index
    });
  }

  handleChangeAuthor(event) {
    const { article } = this.state;
    const index = toInteger(event.target.value);
    article.author = this.state.authors[toInteger(event.target.value)];
    this.setState({
      article,
      authorIndex: index
    });
  }

  handleCreate(event) {
    event.preventDefault();
    axios
      .post(config.API.BLOG + "resources/articles/create", this.state.article)
      .then((res) => {
        console.log("Created new article successfully");
      })
      .catch((err) => {
        console.error(err);
      })
      .finally(() => {
        if (isFunction(this.props.callback)) {
          this.props.callback();
        }
      });
  }

  render() {
    return (
      <Container>
        <Form style={{ marginTop: "20px" }}>
          <Form.Group controlId="titleInput">
            <Form.Label>Title:</Form.Label>
            <Form.Control
              type="text"
              value={this.state.article.title || ""}
              placeholder="Enter article title..."
              onChange={this.handleChangeTitle}
            />
          </Form.Group>
          <Form.Group controlId="descriptionInput">
            <Form.Label>Description:</Form.Label>
            <Form.Control
              as="textarea"
              rows={5}
              value={this.state.article.description || ""}
              placeholder="Enter article description..."
              onChange={this.handleChangeDescription}
            />
          </Form.Group>
          <Form.Group controlId="sightSelect">
            <Form.Label>Sight:</Form.Label>
            <Form.Control
              as="select"
              value={this.state.sightIndex}
              onChange={this.handleChangeSight}
            >
              {this.state.sights.map((sight, index) => (
                <option
                  value={index}
                  key={"sight-" + index}
                >{`${sight.city} - ${sight.name}`}</option>
              ))}
            </Form.Control>
          </Form.Group>
          <Form.Group controlId="authorSelect">
            <Form.Label>Author:</Form.Label>
            <Form.Control
              as="select"
              value={this.state.authorIndex}
              onChange={this.handleChangeAuthor}
            >
              {this.state.authors.map((author, index) => (
                <option
                  key={"author-" + index}
                  value={index}
                >{`${author.firstname} ${author.lastname}`}</option>
              ))}
            </Form.Control>
          </Form.Group>
          <Form.Group controlId="datepickerInput">
            <Form.Label>Publication Date:</Form.Label>
            <Form.Control
              type="date"
              value={this.state.article.publicationDate || ""}
              onChange={this.handleChangeDate}
            />
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
