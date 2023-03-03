function SelectCategories({categories, setSelectedCategory}) {
    return <select className={'form-control mt-3'} onChange={e => setSelectedCategory(e.currentTarget.value)}>
        <option value=''>Всі</option>
        {categories.map(category => <option key={category}>{category}</option>)}
    </select>
}

export default SelectCategories;