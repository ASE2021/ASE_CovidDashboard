import {Browser, LatLng, Layer, polygon} from 'leaflet';
import {LayerEventControlOptions} from './layer-event-control-options';
import {CovidDataMap} from './covid-data-map';

export class ProvinceCoordinates {
  public provinceName: string;
  public provinceId: string;
  public coordinates: number[][][];
  public border: number[][][];
  private covidData: CovidDataMap;
  private districts: ProvinceCoordinates[];
  private layers: Layer[];

  constructor(provinceName: string, provinceId: string, border: any, beizrke?: ProvinceCoordinates[]) {
    this.provinceName = provinceName;
    this.provinceId = provinceId;
    this.districts = beizrke;
    this.border = border.map(item => item.length === 1 && item[0].length > 2 ? [...item[0]] : item) || [];
  }

  public getCovidData(): CovidDataMap {
    return this.covidData;
  }

  public getDistricts(): ProvinceCoordinates[] {
    return this.districts;
  }

  createBorderMapLayers(eventOptions: LayerEventControlOptions): Layer[] {
    this.layers = this.border.map(region => polygon(region.map(
      item => new LatLng(Number(item[0]) > Number(item[1]) ? Number(item[0]) : Number(item[1]),
        Number(item[0]) > Number(item[1]) ? Number(item[1]) : Number(item[0]))),
      {
        attribution: !this.districts ? this.provinceName : this.provinceId, ...this.defaultStyle(this.covidData),
      })
      .on('click', (event => {
        if (eventOptions && eventOptions.onClick) {
          eventOptions.onClick(event);
        }
      }))
      .on('mouseover', (event => {
        this.highlightFeature(event, this.covidData);
        if (eventOptions && eventOptions.onMouseEnter) {
          eventOptions.onMouseEnter(event, `<div class="map-info-box">
        <ul>
            <li><span>${!this.districts ? 'District' : 'Province'}:</span> ${this.covidData.provinceName}</li>
            <li><span>Active cases:</span> ${this.covidData.activeCases} / ${this.covidData.activeCasesRelative}</li>
            <li><span>Deaths:</span> ${this.covidData.sumDeaths} / ${this.covidData.sumDeathsRelative}</li>
            <li><span>Cured:</span> ${this.covidData.sumCured} / ${this.covidData.sumCuredRelative}</li>
        </ul></div>`);
        }
      }))
      .on('mouseout', (event => {
        this.resetHighlight(event, this.covidData);
        if (eventOptions && eventOptions.onMouseOut) {
          eventOptions.onMouseOut(event);
        }
      })));
    return this.layers;
  }

  highlightFeature(event: any, data: CovidDataMap): any {
    let layer = [event.target];
    if (!data || data.provinceId.toString().length > 1) {
      layer = this.layers;
    }

    layer.forEach(l => {
      l.setStyle({
        weight: data && data.provinceId.length > 1 ? 2 : 4,
        opacity: 1,
        color: this.coloringForCases(data),
        fillColor: this.coloringForCases(data),
        fillOpacity: 0.2,
      });
      if (!Browser.ie && !Browser.opera && !Browser.edge) {
        l.bringToFront();
      }

    });


  }

  private coloringForCases(data: CovidDataMap): string {
    if (!data) {
      return '#DE6501';
    }
    if (data.activeCasesRelative < 100) {
      return '#72C600';
    }
    if (data.activeCasesRelative < 150) {
      return '#c0de39';
    }
    if (data.activeCasesRelative < 200) {
      return '#E5B100';
    }
    if (data.activeCasesRelative < 250) {
      return '#E57D00';
    }
    if (data.activeCasesRelative < 300) {
      return '#E55E00';
    } else {
      return '#E50000';
    }
  }

  resetHighlight(event: any, data: CovidDataMap): any {
    let layer = [event.target];
    if (!data || data.provinceId.toString().length > 1) {
      layer = this.layers;
    }
    layer.forEach(l => l.setStyle(this.defaultStyle(data)));
  }

  defaultStyle(data: CovidDataMap): any {
    return {
      weight: data && data.provinceId.length > 1 ? 1 : 2,
      opacity: 0.4,
      color: this.coloringForCases(data),
      fillColor: this.coloringForCases(data),
    };
  }

  createColoredLayers(color: string): Layer[] {
    return this.coordinates.map(item => polygon(
      item.map(c => c.map(i => [i[0], i[1]])),
      {fill: true, fillColor: color, opacity: 0.6, weight: 0}));
  }

  updateCovidInfo(covidDataMap: CovidDataMap): void {
    this.covidData = covidDataMap;
  }

  hasCovidInformationForDistricts(): boolean {
    return this.districts.every(district => !!district.getCovidData());
  }
}

