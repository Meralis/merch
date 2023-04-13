function ChildListCategory({children, parentName, handleCategoryChange}) {

    if (!children || !children.length) {
        return null;
    }
    const handleClick = (event, name) => {
        event.stopPropagation();
        handleCategoryChange(`${parentName}.${name}`);
    };

    return (
        <ul className={'main-nav'}>
            {children.map(child => (
                <li key={child.name} onClick={(event) => handleClick(event, child.name)}>
                    {child.name}
                    <ChildListCategory children={child.children} parentName={`${parentName}.${child.name}`}
                                       handleCategoryChange={handleCategoryChange}/>
                </li>
            ))}
        </ul>
    );
}

export default ChildListCategory;