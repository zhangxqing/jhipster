export interface IChina {
    id?: number;
    name?: string;
    pId?: number;
}

export class China implements IChina {
    constructor(public id?: number, public name?: string, public pId?: number) {}
}
