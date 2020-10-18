export interface ISearch {
    number?: string;
    birth?: string;
    nativePlace?: string;
    stature?: string;
}

export class SchoolgirlSearchModel implements ISearch {
    constructor(public number?: string, public birth?: string, public nativePlace?: any, public stature?: string) {}
}
