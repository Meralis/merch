import {useContext, useState} from "react";
import Basket from "./Basket";
import ProductContext from "../../context/ProductContext";

function BasketIcon() {
    const [isBasketOpen, setIsBasketOpen] = useState(false);
    const [products] = useContext(ProductContext);
    const productsInBasket = products.filter(product => product.addedToBasket);

    function handleShowBasket() {
        setIsBasketOpen(!isBasketOpen);
    }

    return <>
        <div>
            <img src={"/images/basket-icon.png"} alt={"basket"} className={'basket-icon'}
                 onClick={handleShowBasket}/> <b>{productsInBasket.length}</b>
        </div>
        {isBasketOpen &&
            <Basket
                products={productsInBasket}
            />
        }
    </>
}

export default BasketIcon;