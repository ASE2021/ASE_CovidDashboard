import {Provinces} from "./Provinces";
import {CovidCasesDaily} from "./covid-cases-daily";
import {CovidDeathsDaily} from "./covid-deaths-daily";
import {HospitalBedsDaily} from "./hospital-beds-daily";

export class TableDataDaily {

  date: String;
  activeCases: CovidCasesDaily;
  deaths: CovidDeathsDaily;
  hospitalizations: HospitalBedsDaily;


}
