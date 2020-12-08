import { Generic } from './generic';

export class Movement implements Generic {
    id: number;
    name: string;
    productCode: string;
    productName: string;
    category: string;
    type: string;
    count: number;
    differenceAmount: number;
    createAt: string;
}
