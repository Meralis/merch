import React from 'react';
import ReactDOM from 'react-dom/client';
import 'bootstrap/dist/css/bootstrap.min.css';
import {createBrowserRouter, RouterProvider,} from "react-router-dom";
import Products from "./components/Products";
import App from "./App";
import Contacts from "./components/static/Contacts";
import {Container} from "react-bootstrap";
import ErrorPage from "./components/static/ErrorPage";
import './index.css'
import ProductItem from "./components/ProductItem";
import Order from "./components/Order";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
        errorElement: <ErrorPage/>,
        children: [{
            path: "/products",
            element: <Products/>
        }, {
            path: "/product/:productId",
            element: <ProductItem/>
        }, {
            path: '/contacts',
            element: <Contacts/>
        }, {
            path: '/order',
            element: <Order/>
        }]
    },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <Container className={'bg-white'}>
            <RouterProvider router={router}/>
        </Container>
    </React.StrictMode>
);