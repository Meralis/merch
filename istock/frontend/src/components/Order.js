import {Button, Col, Form, Row} from "react-bootstrap";

function Order() {
    return (
        <Form>
            <div className={'mt-4'}><h3>Ваші дані</h3></div>
            <Row>
                <Form.Group as={Col} controlId="formGridFirstName">
                    <Form.Label>Ім'я</Form.Label>
                    <Form.Control type="text"/>
                </Form.Group>
                <Form.Group as={Col} controlId="formGridLastName">
                    <Form.Label>Прізвище</Form.Label>
                    <Form.Control type="text"/>
                </Form.Group>
            </Row>
            <Row>
                <Form.Group as={Col} controlId="formGridEmail">
                    <Form.Label>Email</Form.Label>
                    <Form.Control type="email"/>
                </Form.Group>
                <Form.Group as={Col} controlId="formGridPassword">
                    <Form.Label>Телефон</Form.Label>
                    <Form.Control type="tel" placeholder="+380"/>
                </Form.Group>
            </Row>
            <div className={'mt-4'}><h3>Доставлення</h3></div>
            <Form.Group controlId="formGridAddress">
                <Form.Label>Адреса</Form.Label>
                <Form.Control/>
            </Form.Group>

            {/*<Row>*/}
            {/*    <Form.Group as={Col} controlId="formGridCity">*/}
            {/*        <Form.Label>City</Form.Label>*/}
            {/*        <Form.Control/>*/}
            {/*    </Form.Group>*/}
            {/*    <Form.Group as={Col} controlId="formGridState">*/}
            {/*        <Form.Label>State</Form.Label>*/}
            {/*        <Form.Control as="select" defaultValue="Choose...">*/}
            {/*            <option>Choose...</option>*/}
            {/*            <option>...</option>*/}
            {/*        </Form.Control>*/}
            {/*    </Form.Group>*/}
            {/*    <Form.Group as={Col} controlId="formGridZip">*/}
            {/*        <Form.Label>Zip</Form.Label>*/}
            {/*        <Form.Control/>*/}
            {/*    </Form.Group>*/}
            {/*</Row>*/}
            {/*<Form.Group id="formGridCheckbox">*/}
            {/*    <Form.Check type="checkbox" label="Check me out"/>*/}
            {/*</Form.Group>*/}
            <Button variant="primary" type="submit" className={'mt-4'}>
                Sign in
            </Button>
        </Form>
    );
}

export default Order;