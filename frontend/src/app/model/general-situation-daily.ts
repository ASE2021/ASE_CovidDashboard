import {CovidCasesDaily} from './covid-cases-daily';
import {CovidDeathsDaily} from './covid-deaths-daily';
import {HospitalBedsDaily} from './hospital-beds-daily';

export class GeneralSituationDaily {

  date: string;
  activeCases: CovidCasesDaily;
  deaths: CovidDeathsDaily;
  hospitalizations: HospitalBedsDaily;


}
