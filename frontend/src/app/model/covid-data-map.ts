export interface CovidDataMap {
  provinceId: string;
  provinceName: string;
  casesPer100Thousand: number;
  activeCases: number;
  deaths: number;
  normalBeds: number;
  intensiveBeds: number;
  geoId?: string;
}
