import {useContext, useState} from "react";
import Basket from "./Basket";
import ProductContext from "../../context/ProductContext";

function BasketIcon() {
    const [isBasketOpen, setIsBasketOpen] = useState(false);
    const [products] = useContext(ProductContext);
    // const productsInBasket = products.filter(product => product.addedToBasket);

    function handleShowBasket() {
        setIsBasketOpen(!isBasketOpen);
    }

    return <>
        <img src={"/images/basket-icon.png"} alt={"basket"} className={'basket-icon'} onClick={handleShowBasket}/>
        {isBasketOpen &&
            <Basket
                products={products.filter(product => product.addedToBasket)}
            />
        }
    </>
}

export default BasketIcon;