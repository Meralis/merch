import {useEffect} from "react";
import {API_URL} from '../constants/constants';

function useFetchProducts(setProducts) {
    useEffect(() => {

        const savedBasket = localStorage.getItem('basketItems');

         fetch(`${API_URL}/product`).then(data => data.json()).then(data => {
            if (savedBasket) {
                let savedItems = JSON.parse(savedBasket);
                for (let product of data) {
                    const savedProduct = savedItems.filter(savedItem => product.productId === savedItem.productId);
                    product.addedToBasket = savedProduct.length > 0;
                    product.count = savedProduct.length ? savedProduct[0].count : 1;
                }
                setProducts(data);
            } else {
                setProducts(data.map(product => ({...product, addedToBasket: false, count: 1})));
            }
        })
    }, []);
}

export default useFetchProducts;
