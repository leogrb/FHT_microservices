import { Link } from "@reach/router";
import React from "react";

import "./notfound.css";

const NotFound = () => {
  return (
    <div id="notFound">
      <h1>The page could not be found.</h1>
      <h2>
        <Link to="/">Return to home page</Link>
      </h2>
    </div>
  );
};

export default NotFound;
