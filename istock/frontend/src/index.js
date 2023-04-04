import React from 'react';
import ReactDOM from 'react-dom/client';
import 'bootstrap/dist/css/bootstrap.min.css';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Products from "./components/product/Products";
import App from "./App";
import Contacts from "./components/static/Contacts";
import {Container} from "react-bootstrap";
import ErrorPage from "./components/static/ErrorPage";
import './index.css'
import ProductItem from "./components/product/ProductItem";
import Order from "./components/order/Order";
import Home from "./components/home/Home";

const router = createBrowserRouter([
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
                path: '/contacts',
                element: <Contacts/>
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
        <Container className={'bg-white'}>
            <RouterProvider router={router}/>
            {/*    <Routes />*/}
            {/*</RouterProvider>*/}
        </Container>
    </React.StrictMode>
);