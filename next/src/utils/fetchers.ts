import {FetcherOption} from "@/type/interface";

class FetcherError extends Error {
  status: number;

  constructor(message: string, status: number) {
    super(message);
    this.name = 'FetcherError';
    this.status = status;
  }
}

// () => Promise<any> : 함수의 반환값이 Promise<any>인 함수
export async function fetchers(
  url: string,
  options: FetcherOption = {},
  data?: any
): Promise<any> {
  // const prefixUrl:string = "https://api.heesang.pro/api/";
  // console.log(prefixUrl+url);
  try {
    const response = await fetch(url, {
      ...options,
      headers: {
        'Content-Type': 'application/json',
        ...(options.headers || {}),
      },
      body: JSON.stringify(data),
    });


    if (!response.ok) {
      const errData = await response.json();
      console.error('fetchers error:', errData);
      return Promise.reject(
        new FetcherError(
          errData.message || 'Something went wrong!',
          errData.status
        )
      );
    }

    return await response.json();

  } catch (error:any) {
    console.error('fetchers error:', error);
    return Promise.reject(error);
  }
}