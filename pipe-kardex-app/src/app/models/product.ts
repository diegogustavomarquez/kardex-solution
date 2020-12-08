import { Generic } from './generic';
import { Category } from './category';

export class Product implements Generic {
    id: number;
    nombre: string;
    code: string;
    name: string;
    category: Category;
    count: number;
    createAt: string;
}
