export const enum HospitalType {
    COMPOSITE = 'COMPOSITE',
    SPECIALIZED = 'SPECIALIZED'
}

export const enum HospitalLevel {
    FIRST_RATE_ONE = 'FIRST_RATE_ONE',
    FIRST_RATE_TWO = 'FIRST_RATE_TWO',
    FIRST_RATE_THREE = 'FIRST_RATE_THREE',
    SECOND_RATE_ONE = 'SECOND_RATE_ONE',
    SECOND_RATE_TWO = 'SECOND_RATE_TWO',
    SECOND_RATE_THREE = 'SECOND_RATE_THREE',
    THIRD_RATE_ONE = 'THIRD_RATE_ONE',
    THIRD_RATE_TWO = 'THIRD_RATE_TWO',
    THIRD_RATE_THREE = 'THIRD_RATE_THREE'
}

export interface IHospital {
    id?: number;
    name?: string;
    hospitalType?: HospitalType;
    hospitalLevel?: HospitalLevel;
    address?: string;
    phone?: string;
    imageUrl?: string;
    intro?: any;
    cityIdId?: number;
}

export class Hospital implements IHospital {
    constructor(
        public id?: number,
        public name?: string,
        public hospitalType?: HospitalType,
        public hospitalLevel?: HospitalLevel,
        public address?: string,
        public phone?: string,
        public imageUrl?: string,
        public intro?: any,
        public cityIdId?: number
    ) {}
}
