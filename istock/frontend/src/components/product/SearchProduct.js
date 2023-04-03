import {Form, FormControl, ListGroup} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";

async function sendSearchRequest(searchText) {
    const response = await fetch('http://localhost:8080/product/search', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: searchText
    });
    return await response.json();
}

function SearchProduct() {
    const [searchText, setSearchText] = useState('');
    const [searchResults, setSearchResults] = useState([]);

    useEffect(() => {
        if (searchText.length > 3) {
            const timeout = setTimeout(() => {
                sendSearchRequest(searchText).then(data => setSearchResults(data));
            }, 1000);
            return () => clearTimeout(timeout);

        } else {
            setSearchResults([]);
        }
    }, [searchText]);

    function handleSearchInputChange(e) {
        setSearchText(e.target.value);
    }

    return (
        <div>
            <Form>
                <FormControl type="text"
                             placeholder="Пошук товарів"
                             className="mr-sm-2"
                             value={searchText}
                             onChange={handleSearchInputChange}
                />
            </Form>
            <ListGroup>
                {searchResults.map((product) => (
                    <Link to={`/product/${product.productId}`} key={product.productId}>
                        <ListGroup.Item action>{product.title}</ListGroup.Item>
                    </Link>
                ))}
            </ListGroup>
        </div>
    );
}

export default SearchProduct;


