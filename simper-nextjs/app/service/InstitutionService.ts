import { Institution } from "@/app/model/Institution";
import BaseService from "./BaseService";

class InstitutionService extends BaseService<Institution> {
    constructor() {
        super('v1', 'institutions');
    }
}

export default InstitutionService;
