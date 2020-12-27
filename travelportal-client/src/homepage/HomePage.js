import React, { useState } from "react";
import Jumbotron from "react-bootstrap/Jumbotron";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import { Container } from "react-bootstrap";

import ArticleList from "../article/ArticleList";
import Article from "../article/Article";

const HomePage = () => {
  const [showModal, setShowModal] = useState(false);
  const handleClose = () => setShowModal(false);
  const handleShow = () => setShowModal(true);
  const modalCallback = () => handleClose();
  return (
    <>
      <Jumbotron fluid>
        <Container>
          <h1>#VisitAustria</h1>
          <h3>Holiday Tips From Travellers Like You</h3>
          <p>
            The first Austrian travel portal & blog for the most popular sights.
            Tell us about your favourite experience and help others plan their
            holiday! Find out about the magical places chosen by Austrians as
            the country&apos;s most beautiful spots. Skiing&Winter,
            Lakes&Nature, Walking&Hiking in Austria and more... Plan your trip
            with us :)
          </p>
          <p>
            <Button variant="primary" onClick={handleShow}>
              Create Article
            </Button>
          </p>
        </Container>
      </Jumbotron>
      <Container>
        <ArticleList />
      </Container>
      <Modal size="lg" show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Create new Article</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Article
            {...{
              callback: modalCallback
            }}
          />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default HomePage;
