export const saveProducts = (updatedProducts) => {
    const productsToSave = updatedProducts.filter(product => product.addedToBasket).map(product => ({
        productId: product.productId,
        count: product.count
    }));
    localStorage.setItem('basketItems', JSON.stringify(productsToSave));
}
