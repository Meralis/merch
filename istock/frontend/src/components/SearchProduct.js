import {Button, Form, InputGroup} from "react-bootstrap";
import React, {useCallback, useContext, useEffect, useState} from "react";
import ProductContext from "../context/ProductContext";
// 1месседж саксесс/ еррор
//     2резулт массив

async function sendSearchRequest(searchText) {
    const response = await fetch('http://localhost:8080/product/search', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: searchText
    });
    return await response.json();
}

function SearchProduct() {
    const [products, setProducts] = useContext(ProductContext);
    const [searchText, setSearchText] = useState(' ');
    const [currentSearch, setCurrentSearch] = useState('');

    const handleSubmit = useCallback((e) => {
        e.preventDefault();
        setSearchText(currentSearch);
    }, [currentSearch]);

    useEffect(() => {
        sendSearchRequest(searchText).then(data => {
            setProducts(data)
        });
    }, [searchText]);

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group controlId="searchText">
                <InputGroup className="mb-2">
                    <Form.Control type="text" placeholder="Search here"
                                  onChange={(e) => setCurrentSearch(e.target.value)}/>
                    <Button variant="outline-secondary" type="submit">Пошук</Button>
                </InputGroup>
                {/*<Form.Select size="sm">*/}
                {/*    {products.map(product => <option value="${product.title}">${product.title}</option>)};*/}
                {/*</Form.Select>*/}
            </Form.Group>
        </Form>
    );
}

export default SearchProduct;
