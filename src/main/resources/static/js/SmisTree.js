//Функция создаётся и сразу вызывается
(function initTree (treeNode) {
    const FOLDER = 'FOLDER';
    const FILE = 'FILE';
    const clickFolder = folder => render(folder);

    //Создание DOM элемента
    function initChild(child) {
        const entity = document.createElement('li');
        entity.innerHTML = (child.type === FOLDER ? './' : '') + child.name;
        entity.setAttribute('class', child.type);
        entity.addEventListener(
            'click',
            () => child.type === FOLDER && clickFolder(child)
    );

        return entity;
    }

    //Создание всех DOM-элементов в папке
    function initFolder(parent) {
        const entity = document.createElement('ul');

        parent.children
            .sort(child => child.type !== FOLDER)
    .forEach(child => entity.appendChild(initChild(child)));

        return entity;
    }

    //Функция создающая возврающая дерево списка с "родителями"
    function getRoot() {
        const withParents = (child, parent) => {
            child.parent = parent;

            if (child && child.children) {
                child.children = child.children.map(
                    subchild => withParents(subchild, child)
            );
            }

            return child;
        };

        return withParents(getMockFolderData());
    }

    //Генерируем дерево списка для примера
    function getMockFolderData() {
        const rootFolder = { type: FOLDER, children: [] };
        let currentFolder = rootFolder;

        for (let size= 0; size < 25; size++) {
            currentFolder  = (
                [
                    currentFolder.parent || currentFolder,
                    ...currentFolder.children.filter(f => f.type === FOLDER)
        ]
        .sort(() => 0.5 - Math.random())
        .pop()
        );

            const FILE_NAMES = ["Фотки", "Любимая папка", "Разное"];
            const FOLDER_NAMES = ["photo1.png", "backup.zip", "42.jpg"];
            currentFolder.children.push(
                Math.random() < 0.5  ? {
                    type:  FOLDER,
                    name: FILE_NAMES.sort(() => Math.random() - 0.5).pop()
            children: []
        }  : {
                name: FOLDER_NAMES.sort(() => Math.random() - 0.5).pop(),
                    type: FILE
            }
        );
        }

        return rootFolder;
    }

    //Рекурсивно по родителям получаем путь к папке
    function getBreadcrumbs(child, arr = []) {
        return (
            child.parent
                ? getBreadcrumbs(child.parent, [child, ...arr])
    : [child, ...arr]
    );
    }

    //Перерисовываем текущую папку
    function render(root) {
        const folder = initFolder(root);
        const breadcrumbs = document.createElement('span');

        getBreadcrumbs(root).forEach(child => {
            const breadcrumb = document.createElement('a');
        breadcrumb.href = "javascript:void(0)";
        breadcrumb.innerHTML = `${child.name || ""} ${child.type === FOLDER ? "/" : ""}`;

        breadcrumb.addEventListener(
            'click',
            () => child.type === FOLDER && render(child)
    );

        breadcrumbs.appendChild(breadcrumb);
    });

        treeNode.innerHTML = "";
        treeNode.appendChild(breadcrumbs);
        treeNode.appendChild(folder);
    }

    render(getRoot());
})(document.getElementById('tree'));