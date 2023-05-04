import {Button, Col, Form, Row} from "react-bootstrap";
import React, {useCallback, useContext, useEffect, useMemo, useState} from "react";
import {getProductById} from "../../utils/getProductById";
import ProductContext from "../../context/ProductContext";
import clearBasket from "../../utils/clearBasket";
import {useNavigate} from "react-router-dom";
import {API_URL} from '../../constants/constants';

export default function OrderForm() {
    const [products, setProducts] = useContext(ProductContext);
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [address, setAddress] = useState('');
    const [phoneError, setPhoneError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = useCallback((e) => {
        e.preventDefault();
        const phoneRegex = /^\d{10}$/;
        if (!phoneRegex.test(phone)) {
            setPhoneError('Некорректний номер телефону');
            return;
        }
        sendOrderData(orderDTO).then(data => {
            popupOrderInfo(data.orderId);
            const updatedProducts = clearBasket(products);
            localStorage.setItem('basketItems', JSON.stringify([]));
            setProducts(updatedProducts);
        })
    }, [firstName, lastName, email, phone, address, setPhoneError]);

    useEffect(() => {
        const productsInBasket = products.filter(product => product.addedToBasket);
        if (productsInBasket.length === 0) {
            navigate('/');
        }
    }, [products])


    const isSubmitActive = useMemo(() => {
        return firstName !== '' && lastName !== '' && email !== '' && phone !== '' && address !== '';
    }, [firstName, lastName, email, phone, address]);

    const savedItems = localStorage.getItem('basketItems');
    let basketItems = JSON.parse(savedItems);

    const clientDTO = {status: null, firstName: firstName, lastName: lastName, email: email, phone: phone};

    const savedProducts = basketItems.map(basketItem => getProductById(basketItem, products)).filter(product => product);

    const orderItemDTO = savedProducts.map(savedProduct => ({
        productId: savedProduct.productId,
        quantity: savedProduct.count,
        amount: (savedProduct.count) * (savedProduct.price)
    }));

    const orderDTO = {
        order_id: null,
        status: null,
        comments: '',
        created: null,
        client: clientDTO,
        total: orderItemDTO.reduce((acc, product) => acc + product.amount, 0),
        deliveryAddress: address,
        items: orderItemDTO
    };

    async function sendOrderData(orderDTO) {
        const response = await fetch(`${API_URL}/order`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(orderDTO)
        });
        return await response.json();
    }

    function popupOrderInfo(orderNumber) {
        window.alert(`Ваше замовлення N ${orderNumber} прийняте. Підтвердження відправлене Вам на електронну пошту.`);
    }

    return (
        <div className={'order_form'}>
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
                        <Form.Control type="tel" placeholder="999 999 99 99" value={phone} name="phone"
                                      onChange={(e) => setPhone(e.target.value)}/>
                        {phoneError && <Form.Text className="text-danger">{phoneError}</Form.Text>}
                    </Form.Group>
                </Row>
                <div className={'mt-4'}><h3>Доставлення</h3></div>
                <Form.Group controlId="address">
                    <Form.Label>Адреса</Form.Label>
                    <Form.Control value={address} name="address"
                                  onChange={(e) => setAddress(e.target.value)}/>
                </Form.Group>
                <Button variant="success" type="submit" size={'sm'} className={'mt-4'} disabled={!isSubmitActive}>
                    Відправити
                </Button>
            </Form>
        </div>
    );
}