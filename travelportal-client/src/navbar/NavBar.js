import React from "react";
import { Link } from "@reach/router";
import { Nav, Navbar } from "react-bootstrap";

const NavBar = () => {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Navbar.Brand as={Link} to="/">
        Travel Portal
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mr-auto">
          <Nav.Link as={Link} to="/">Articles</Nav.Link>
          <Nav.Link as={Link} to="/bonus">Author Bonus</Nav.Link>
          <Nav.Link as={Link} to="/statistics">Statistics</Nav.Link>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default NavBar;
