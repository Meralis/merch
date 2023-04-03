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
    const [isOpen, setIsOpen] = useState(true);

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
        setIsOpen(true);
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
            {isOpen && (
                <ListGroup>
                    {searchResults.map((product) => (
                        <Link to={`/product/${product.productId}`}
                              key={product.productId}
                              onClick={() => {
                                  setIsOpen(false);
                                  setSearchText('')
                              }}>
                            <ListGroup.Item action>{product.title}</ListGroup.Item>
                        </Link>
                    ))}
                </ListGroup>
            )}
        </div>
    );
}

export default SearchProduct;


