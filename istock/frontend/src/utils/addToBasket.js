function addToBasket(products, productId) {
    return products.map(product => ({
        ...product,
        addedToBasket: productId === product.productId ? true : product.addedToBasket
    }));
}
export default addToBasket;