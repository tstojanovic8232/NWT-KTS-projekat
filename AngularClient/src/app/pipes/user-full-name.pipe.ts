import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'userFullName'
})
export class UserFullNamePipe implements PipeTransform {

  transform(firstName: string, lastName: string): string {
    return `${firstName} ${lastName}`;
  }

}
