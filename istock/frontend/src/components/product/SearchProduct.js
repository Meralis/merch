import {Form, FormControl, ListGroup} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {API_URL} from "../../constants/constants"

async function sendSearchRequest(searchText) {
    const response = await fetch(`${API_URL}/product/search`, {
        method: 'POST',
        body: searchText
    });
    return await response.json();
}

function SearchProduct() {
    const [searchText, setSearchText] = useState('');
    const [searchResults, setSearchResults] = useState([]);
    const [isSearchOpen, setIsSearchOpen] = useState(true);

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
        setIsSearchOpen(true);
    }

    return (
        <div>
            <Form>
                <FormControl type="text"
                             placeholder="Пошук"
                             className="mr-sm-2"
                             value={searchText}
                             onChange={handleSearchInputChange}
                />
            </Form>
            {isSearchOpen && (
                <ListGroup className={'select_list'}>
                    {searchResults.map((product) => (
                        <Link to={`/product/${product.productId}`}
                              key={product.productId}
                              onClick={() => {
                                  setIsSearchOpen(false);
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


