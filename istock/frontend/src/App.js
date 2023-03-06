import {Nav, Navbar} from "react-bootstrap";
import Products from "./components/Products";
import {Link} from "react-router-dom";

function App() {
    return (
        <>
            <Navbar bg="primary" variant="dark" className={'row'}>
                <Nav className="me-auto">
                    <Link className={'nav-link'} to={'/'}>Home</Link>
                    <Link className={'nav-link'} to={'/products'}>Products</Link>
                    <Link className={'nav-link'} to={'/contacts'}>Contacts</Link>
                </Nav>
            </Navbar>
            <Products/>
        </>
    );
}

export default App;