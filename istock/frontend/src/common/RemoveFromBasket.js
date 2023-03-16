import {saveProducts} from "./saveProducts";

function removeFromBasket(productId, products, setProducts) {
    const newProducts = products.map(product => ({
        ...product,
        addedToBasket: productId === product.productId ? false : product.addedToBasket,
        count: productId === product.productId ? 1 : product.count
    }))
    setProducts(newProducts);
    saveProducts(newProducts);
}
export default removeFromBasket;