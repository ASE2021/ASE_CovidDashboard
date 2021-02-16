export interface CovidDataMap {
  provinceId: string;
  provinceName: string;
  activeCases: number;
  activeCasesRelative: number;
  sumDeaths: number;
  sumDeathsRelative: number;
  sumCured: number;
  sumCuredRelative: number;
  sumCased: number;
  sumCasedRelative: number;
  geoId?: string;
}
