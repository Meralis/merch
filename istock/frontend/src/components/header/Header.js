import {Nav, Navbar} from "react-bootstrap";
import {Link} from "react-router-dom";
import SearchProduct from "../product/SearchProduct";
import BasketIcon from "../basket/BasketIcon";

function Header() {
    return <>
        <Navbar className={'row color-dark-theme'}>
            <Nav className="w-100 justify-content-around align-items-center">
                <Link className={'nav-link'} to={'/'}>Istock</Link>
                <Link className={'nav-link'} to={'/products'}>Каталог</Link>
                <SearchProduct className={'search'}/>
                <BasketIcon/>
                <Link className={'nav-link'} to={'/delivery'}>Доставка</Link>
            </Nav>
        </Navbar>
    </>
}

export default Header;