// 회원가입 API 호출 함수
import {fetchers} from "@/utils/fetchers";

export const registration = async (data: RegistrationData) => {
  try {
    const response:Promise<RegistrationResponse> =
      await fetchers('/api/registration', {method: 'POST'}, data);
    return response;
  } catch (error) {
    throw error;
  }
};