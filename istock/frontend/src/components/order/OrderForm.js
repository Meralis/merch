import {Button, Col, Form, Row} from "react-bootstrap";
import React, {useCallback, useContext, useMemo, useState} from "react";
import {getProductById} from "../../utils/getProductById";
import ProductContext from "../../context/ProductContext";

export default function OrderForm({basketItems}) {
    const [products, setProducts] = useContext(ProductContext);

    const [firstName, setFirstName] = useState('');

    const [lastName, setLastName] = useState('');

    const [email, setEmail] = useState('');

    const [phone, setPhone] = useState('');

    const [address, setAddress] = useState('');

    const handleSubmit = useCallback((e) => {
        e.preventDefault();
    }, [firstName, lastName, email, phone, address])

    const isSubmitActive = useMemo(() => {
        return firstName !== '' && lastName !== '' && email !== '' && phone !== '' && address !== '';
    }, [firstName, lastName, email, phone, address])

    const clientDTO = {status: '', firstName: firstName, lastName: lastName, email: email, phone: phone};

    const savedProducts = basketItems.map(basketItem => getProductById(basketItem, products)).filter(product => product);

    const orderItemDTO = savedProducts.map(savedProduct => ({
        product: savedProduct.productId,
        order: '',
        quantity: savedProduct.count,
        amount: (savedProduct.count) * (savedProduct.price)
    }));

    console.log('orderItemDTO ', orderItemDTO);


    return (
        <Form onSubmit={handleSubmit}>
            <div className={'mt-4'}><h3>Ваші дані</h3></div>
            <Row>
                <Form.Group as={Col} controlId="firstName">
                    <Form.Label>Ім'я</Form.Label>
                    <Form.Control type="text" value={firstName} name="firstName"
                                  onChange={(e) => setFirstName(e.target.value)}/>
                </Form.Group>
                <Form.Group as={Col} controlId="lastName">
                    <Form.Label>Прізвище</Form.Label>
                    <Form.Control type="text" value={lastName} name="lastName"
                                  onChange={(e) => setLastName(e.target.value)}/>
                </Form.Group>
            </Row>
            <Row>
                <Form.Group as={Col} controlId="email">
                    <Form.Label>Email</Form.Label>
                    <Form.Control type="email" value={email} name="email"
                                  onChange={(e) => setEmail(e.target.value)}/>
                </Form.Group>
                <Form.Group as={Col} controlId="phone">
                    <Form.Label>Телефон</Form.Label>
                    <Form.Control type="tel" placeholder="+380" value={phone} name="phone"
                                  onChange={(e) => setPhone(e.target.value)}/>
                </Form.Group>
            </Row>
            <div className={'mt-4'}><h3>Доставлення</h3></div>
            <Form.Group controlId="address">
                <Form.Label>Адреса</Form.Label>
                <Form.Control value={address} name="address"
                              onChange={(e) => setAddress(e.target.value)}/>
            </Form.Group>
            <Button variant="primary" type="submit" className={'mt-4'} disabled={!isSubmitActive}>
                Відправити
            </Button>
        </Form>
    );
}


async function sendOrderData() {
    const response = await fetch('http://localhost:8080/order', {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({})
    });
    const data = await response.json();
}



