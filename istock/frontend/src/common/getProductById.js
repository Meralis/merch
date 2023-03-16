export const getProductById = (savedItem, products) => {
    const foundProducts = products.filter(product => product.productId === savedItem.productId);
    if (foundProducts.length > 0) {
        return {...savedItem, ...foundProducts[0]};
    }
    return undefined;
}