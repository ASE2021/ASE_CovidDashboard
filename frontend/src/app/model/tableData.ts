import {Provinces} from "./Provinces";
import {CovidCasesDaily} from "./covid-cases-daily";
import {CovidDeathsDaily} from "./covid-deaths-daily";
import {CovidHospitalizationsDaily} from "./covid-hospitalizations-daily";

export interface TableData {

  provinces: Provinces;
  activeCases: CovidCasesDaily;
  deaths: CovidDeathsDaily;
  hospitalizations: CovidHospitalizationsDaily;


}
