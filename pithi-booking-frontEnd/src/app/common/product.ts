export class Product {
       constructor(
                public id?: string,
                public name?: string,
                public description?: string,
                public price?: number,
                public imageUrl?: string,
                public active?: boolean,
                public address?: string,
                public availability?: Date ,
                public dateCreated?: Date,
                public lastUpdated?: Date
        ) {}
}

