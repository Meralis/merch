import React from 'react';
import ReactDOM from 'react-dom/client';
import 'bootstrap/dist/css/bootstrap.min.css';
import {createHashRouter, RouterProvider} from "react-router-dom";
import Products from "./components/product/Products";
import App from "./App";
import Delivery from "./components/static/Delivery";
import {Container} from "react-bootstrap";
import ErrorPage from "./components/static/ErrorPage";
import './css/index.css'
import ProductItem from "./components/product/ProductItem";
import Order from "./components/order/Order";
import Home from "./components/home/Home";

const router = createHashRouter([
    {
        path: "/",
        element: <App/>,
        errorElement: <ErrorPage/>,
        children: [{
            path: "/",
            element: <Home/>
        }, {
            path: "/products",
            element: <Products/>
        }, {
            path: "/product/:productId",
            element: <ProductItem/>
        }, {
            path: '/delivery',
            element: <Delivery/>
        }, {
            path: '/order',
            element: <Order/>
        }
        ]
    }
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <Container className={'color-light-theme container'}>
            <RouterProvider router={router}/>
        </Container>
    </React.StrictMode>
);