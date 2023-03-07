import {Nav, Navbar} from "react-bootstrap";
import {Link, Outlet} from "react-router-dom";

function App() {
    return (
        <>
            <Navbar bg="primary" variant="dark" className={'row'}>
                <Nav className="me-auto">
                    <Link className={'nav-link'} to={'/products'}>Products</Link>
                    <Link className={'nav-link'} to={'/contacts'}>Contacts</Link>
                </Nav>
            </Navbar>
            <Outlet/>
        </>
    );
}

export default App;