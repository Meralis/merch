function clearBasket(products) {
    return products.map(product => ({
        ...product,
        addedToBasket: false,
        count: 1
    }));
}

export default clearBasket;