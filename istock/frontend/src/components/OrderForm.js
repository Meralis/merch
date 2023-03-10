import {Button, Col, Form, Row} from "react-bootstrap";
import React from "react";

export default class OrderForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            phone: '',
            address: ''
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value})
    }

    handleSubmit(event) {
        this.sendOrderData().then(data => data.json());
        event.preventDefault();
    }

    async sendOrderData() {
        const formData = new FormData;
        formData.append("firstName", this.state.firstName);
        formData.append("lastName", this.state.lastName);
        formData.append("email", this.state.email);
        formData.append("phone", this.state.phone);
        formData.append("address", this.state.address);
        const response = await fetch('http://localhost:8080/order', {
            method: 'PUT',
            body: formData
        })
        const data = await response.json();
    }

    render() {
        return (
            <Form onSubmit={this.handleSubmit}>
                <div className={'mt-4'}><h3>Ваші дані</h3></div>
                <Row>
                    <Form.Group as={Col} controlId="firstName">
                        <Form.Label>Ім'я</Form.Label>
                        <Form.Control type="text" value={this.state.value} name="firstName"
                                      onChange={this.handleChange}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="lastName">
                        <Form.Label>Прізвище</Form.Label>
                        <Form.Control type="text" value={this.state.value} name="lastName"
                                      onChange={this.handleChange}/>
                    </Form.Group>
                </Row>
                <Row>
                    <Form.Group as={Col} controlId="email">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" value={this.state.value} name="email" onChange={this.handleChange}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="phone">
                        <Form.Label>Телефон</Form.Label>
                        <Form.Control type="tel" placeholder="+380" value={this.state.value} name="phone"
                                      onChange={this.handleChange}/>
                    </Form.Group>
                </Row>
                <div className={'mt-4'}><h3>Доставлення</h3></div>
                <Form.Group controlId="address">
                    <Form.Label>Адреса</Form.Label>
                    <Form.Control value={this.state.value} name="address" onChange={this.handleChange}/>
                </Form.Group>
                <Button variant="primary" type="submit" className={'mt-4'}>
                    Відправити
                </Button>
            </Form>
        );
    }
}

