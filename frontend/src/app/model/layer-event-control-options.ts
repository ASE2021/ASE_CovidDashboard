export interface LayerEventControlOptions {
  onClick?: (event: any) => void;
  onDblClick?: (event: any) => void;
  onMouseEnter?: (event: any, covidInfoString: string) => void;
  onMouseOut?: (event: any) => void;
}
