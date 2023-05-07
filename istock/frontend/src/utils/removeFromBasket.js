function removeFromBasket(products, productId) {
    return products.map(product => ({
        ...product,
        addedToBasket: productId === product.productId ? false : product.addedToBasket,
        count: productId === product.productId ? 1 : product.count
    }));
}
export default removeFromBasket;